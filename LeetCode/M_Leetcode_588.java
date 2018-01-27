package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 588. Design In-Memory File System

 Design an in-memory file system to simulate the following functions:

 ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

 mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

 addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

 readContentFromFile: Given a file path, return its content in string format.

 Example:
 Input:
 ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 Output:
 [null,[],null,null,["a"],"hello"]
 Explanation:
 filesystem
 Note:
 You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
 You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
 You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
 */
public class M_Leetcode_588 {
    class FileSystem {
        class INode{
            String name;
            boolean isDir = true;
            String content = "";
            Map<String, INode> children = new TreeMap<>();

            public INode(String name, boolean isDir){
                this.name = name;
                this.isDir = isDir;
            }
        }

        INode root = new INode("/", true);

        public FileSystem() {
            //no-op
        }

        public List<String> ls(String path) {
            INode cur = root;
            if(path.length() > 1){
                String[] dir = path.substring(1).split("/");
                for(String d : dir){
                    cur = cur.children.get(d);
                }
            }
            if(cur == null) return new ArrayList<>();
            if(cur.isDir) return new ArrayList<>(cur.children.keySet());
            List<String> res = new ArrayList<>();
            res.add(cur.name);
            return res;
        }

        public void mkdir(String path) {
            String[] dir = path.substring(1).split("/");
            INode cur = root;
            for(String d : dir){
                if(!cur.children.containsKey(d)) cur.children.put(d, new INode(d, true));
                cur = cur.children.get(d);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] dir = filePath.substring(1).split("/");
            INode cur = root;
            for(String d : dir){
                if(!cur.children.containsKey(d)) cur.children.put(d, new INode(d, true));
                cur = cur.children.get(d);
            }
            cur.isDir = false;
            cur.content += content;
        }

        public String readContentFromFile(String filePath) {
            String[] dir = filePath.substring(1).split("/");
            INode cur = root;
            for(String d : dir){
                cur = cur.children.get(d);
            }
            return cur.content;
        }
    }
}
