package test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alipay.vehicleindustrycore.common.service.facade.model.VehicleElement;

public class TestSort {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<VehicleElement> list = new ArrayList<VehicleElement>();
        for (int i = 0; i < 5; i++) {
            VehicleElement vehicleElement = new VehicleElement();
            vehicleElement.setPinyin(String.valueOf(i));
            list.add(vehicleElement);
        }
        System.out.println(JSONObject.toJSON(list));
        
        /***/
        for (int i = list.size(); i >= 0; i--) {
            if(list.get(i).getPinyin().equals("2")){
                list.remove(i);
                continue;
            }
        }
        /**
        for(VehicleElement vehicleElement:list){
            if(vehicleElement.getPinyin().equals("2")){
                list.remove(vehicleElement);
                break;
            }
        }
        */
        System.out.println(JSONObject.toJSON(list));
        
    }

}
