// Time Complexity : O(1) for next() and amortised O(1) , O(d) for hasNext(), where d is the depth of the nested list
// Space Complexity : O(d) for the stack, where d is the depth of the nested list
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// For controlling the recursion, we use a stack of iterators of the class NestedInteger
// In hasNext(), we try to load the list with a Integer it it finds one, else it keeps pushing the iterators of the lists it finds
// Is the iterator does not have any more elements, we pop it off the stack
// We store the next Integer in a variable nextEl, to return when next is called

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext())
                st.pop();
            else if( (nextEl = st.peek().next()).isInteger())
                return true;
            else
                st.push(nextEl.getList().iterator());
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */