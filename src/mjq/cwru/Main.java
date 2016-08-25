package mjq.cwru;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    public class Key implements Comparable {
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public class Value {
    }

    /**
     * it has bug
     *
     * @param <Key>
     * @param <Value>
     */
    public static class SeparateChainingHashST<Key, Value> {
        private int M = 97;//number of chains
        private Node[] st = new Node[M];

        private static class Node {
            private Object key;
            private Object val;
            private Node next;

        }

        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % M;
        }

        public void put(Key key, Value val) {
            int i = hash(key);
            for (Node x = st[i]; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }
//            need to be fixed
//            st[i] = new Node(key, val, st[i]);
        }

        public Value get(Key key) {
            int i = hash(key);
            for (Node x = st[i]; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    return (Value) x.val;
                }
            }
            return null;
        }
    }

    public class LinearProbingHashST<Key, Value> {
        private int M = 30001;
        private Value[] vals = (Value[]) new Object[M];
        private Key[] keys = (Key[]) new Object[M];

        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % M;
        }

        public void put(Key key, Value val) {
            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) / M) {
                if (keys[i].equals(key))
                    break;
            }
            keys[i] = key;
            vals[i] = val;
        }

        public Value get(Key key) {
            for (int i = hash(key); keys[i] != null; i = (i + 1) / M) {
                if (key.equals(keys[i]))
                    return vals[i];
            }
            return null;
        }

    }

}
