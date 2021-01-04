package L24_tree;

/**
 * User: Hanxu
 * Date: 1/4/2021
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class delete2 {
    public TreeNode deleteNode2(TreeNode root, int key){
        // https://www.techiedelight.com/deletion-from-bst/

        // pointer to store parent node of current node
        TreeNode parent = null;

        // start with root node
        TreeNode curr = root;

        // search key in BST and set its parent pointer
        while (curr != null && curr.val != key)
        {
            // update parent node as current node
            parent = curr;

            // if given key is less than the current node, go to left subtree
            // else go to right subtree
            if (key < curr.val) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        // return if key is not found in the tree
        if (curr == null) {
            return root;
        }

        // Case 1: node to be deleted has no children i.e. it is a leaf node
        if (curr.left == null && curr.right == null)
        {
            // if node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != root) {
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // if tree has only root node, delete it and set root to null
            else {
                root = null;
            }
        }

        // Case 2: node to be deleted has two children
        else if (curr.left != null && curr.right != null)
        {
            // find its in-order successor node
            TreeNode successor  = minimumKey(curr.right);

            // store successor value
            int val = successor.val;

            // recursively delete the successor. Note that the successor
            // will have at-most one child (right child)
            deleteNode(root, successor.val);

            // Copy the value of successor to current node
            curr.val = val;
        }

        // Case 3: node to be deleted has only one child
        else
        {
            // find child node
            TreeNode child = (curr.left != null)? curr.left: curr.right;

            // if node to be deleted is not a root node, then set its parent
            // to its child
            if (curr != root)
            {
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }

            // if node to be deleted is root node, then set the root to child
            else {
                root = child;
            }
        }

        return root;
    }
    // Helper function to find minimum value node in subtree rooted at curr
    public static TreeNode minimumKey(TreeNode curr)
    {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        // wang zheng's version. a bit hard to understand.
        TreeNode p = root; // p指向要删除的节点，初始化指向根节点
        TreeNode pp = null; // pp记录的是p的父节点
        while (p != null && p.val != key) {
            pp = p;
            if (key > p.val) p = p.right;
            else p = p.left;
        }
        if (p == null) return root; // 没有找到

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            TreeNode minP = p.right;
            TreeNode minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.val = minP.val; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) root = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}