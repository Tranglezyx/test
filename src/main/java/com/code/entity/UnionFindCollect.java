package com.code.entity;

/**
 * @author trangle
 * <p>
 * 并查集
 */
public class UnionFindCollect {

    /**
     * 存储父节点数组，下标为当前，下标对应的值为父节点的值
     * 如果不存在父级或自己为根节点，则下标等于值
     */
    public int[] parent;

    /**
     * 层级，初始为0
     */
    public int[] rank;

    /**
     * 初始化，默认所有节点都是独立的
     *
     * @param length 长度
     */
    public UnionFindCollect(int length) {
        this.parent = new int[length];
        this.rank = new int[length];
        for (int i = 0; i < length; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * 合并两个数字所分别代表的并查集
     *
     * @param x 需要合并的值
     * @param y 需要合并的值
     */
    public void union(int x, int y) {
        // 分别查出两个数字的根节点
        int left = find(x);
        int right = find(y);
        // 根节点相同说明是同一个并查集，不需要做合并
        if (left == right) {
            return;
        }
        // 如果x所在树的层级小于y所在树的层级，则把y的根节点作为x的根节点的父节点
        if (rank[left] < rank[right]) {
            parent[left] = right;
        } else {
            // 反之则把x的根节点作为y的根节点的父节点
            parent[right] = left;
            // 如果两者层级相同，则需要把作为父节点的层级+1
            if (rank[left] == rank[right]) {
                rank[left]++;
            }
        }
    }

    /**
     * 查询当前值的根节点
     *
     * @param num 查询值
     * @return 查询值的根节点
     */
    public int find(int num) {
        // 如果下标等于值，则说明当前值已经是根节点
        if (parent[num] == num) {
            return num;
        }
        // 如果不相等，则递归查询，根据parent数组里的值等于父节点的下标原理进行递归
        return find(parent[num]);
    }
}
