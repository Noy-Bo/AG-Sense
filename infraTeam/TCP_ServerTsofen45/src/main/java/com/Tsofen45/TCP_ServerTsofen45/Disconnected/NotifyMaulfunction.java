package com.Tsofen45.TCP_ServerTsofen45.Disconnected;

import java.util.HashMap;
import java.util.ArrayList;

public class NotifyMaulfunction implements Runnable {
    private static HashMap<String,NodeIndex> imei_list = null;
    private static ArrayList[] lists_ = null;
    private static String current_imei = null;
    public  NotifyMaulfunction(String imei){
        current_imei = imei;
        if(lists_ == null){
            lists_ = new ArrayList[13];
            for(int i = 0 ; i < 13 ; i++)
                lists_[i] = new ArrayList<String>();
            imei_list = new HashMap<>();
            load_imei();
        }
    }
    class NodeIndex{
        private ArrayList<String> ListHead = null;
        private int index = -1;
        public NodeIndex(ArrayList<String> list_,int index_){
            ListHead = list_;
            index = index_;
        }

        public ArrayList<String> getListHead() {
            return ListHead;
        }

        public void setListHead(ArrayList<String> listHead) {
            ListHead = listHead;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
    public static HashMap<String, NodeIndex> getImei_list() {
        return imei_list;
    }

    public static void setImei_list(HashMap<String, NodeIndex> imei_list) {
        NotifyMaulfunction.imei_list = imei_list;
    }

    public static ArrayList[] getLists_() {
        return lists_;
    }

    public static void setLists_(ArrayList[] lists_) {
        NotifyMaulfunction.lists_ = lists_;
    }

    public static void load_imei(){

    }
    @Override
    public void run() {
        ArrayList<String> arr_ = imei_list.get(current_imei).getListHead();
        String curr_node = arr_.get(imei_list.get(current_imei).getIndex());
        arr_.remove(curr_node);
        lists_[0].add(curr_node);
        imei_list.put(current_imei,new NodeIndex(lists_[0],lists_[0].size()-1));
    }
}
