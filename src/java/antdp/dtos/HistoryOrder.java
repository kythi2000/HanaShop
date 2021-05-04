/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class HistoryOrder implements Serializable{
    private HashMap<String, List<FoodOrderDTO>> historyOrder;

    public HistoryOrder() {
        this.historyOrder = new HashMap<>();
    }

    
    public HashMap<String, List<FoodOrderDTO>> getHistoryOrder() {
        return historyOrder;
    }

    public void setHistoryOrder(HashMap<String, List<FoodOrderDTO>> historyOrder) {
        this.historyOrder = historyOrder;
    }
    public void add(String orderId, List<FoodOrderDTO> list){
        if(this.historyOrder == null){
            this.historyOrder = new HashMap<>();
        }
        this.historyOrder.put(orderId, list);
    }
    public void remove(String id){
        if(this.historyOrder.containsKey(id)){
            this.historyOrder.remove(id);
        }
    }
}
