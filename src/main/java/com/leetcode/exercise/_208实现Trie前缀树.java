package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (实现Trie前缀树)
 * @date 2021/4/14 16:49
 */
public class _208实现Trie前缀树 {

    private class trieNode {
        private boolean isEnd = false;
        private trieNode[] next;

        public trieNode() {
            next = new trieNode[26];
        }
    }

    private trieNode root;

    public _208实现Trie前缀树() {
        root = new trieNode();
    }

    //插入单词
    public void insert(String word) {
        trieNode node=root;
        for (int i = 0, ch, len = word.length(); i < len; i++) {
            ch=word.charAt(i)-'a';
            if (node.next[ch]==null){
                node.next[ch]=new trieNode();
            }
            //把node节点种存储字母的地址给下一个节点继续判断
            node=node.next[ch];
        }
        node.isEnd=true;
    }

    //判断是否有这个单词
    public boolean search(String word) {
        trieNode node=root;
        for (int i = 0, ch, len = word.length(); i < len; i++){
            ch=word.charAt(i)-'a';
            if (node.next[ch]==null) return false;
            node=node.next[ch];
        }
        return node.isEnd;
    }

    //虽然不是单词,但是存储了这个前缀
    public boolean startsWith(String prefix) {
        trieNode node=root;
        for (int i = 0, ch, len = prefix.length(); i < len; i++){
            ch=prefix.charAt(i)-'a';
            if (node.next[ch]==null) return false;
            node=node.next[ch];
        }
        return true;
    }

}
