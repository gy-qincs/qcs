package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("unchecked")
public class TestSlf4j {

    public static void main(String[] args) {
        System.out.println(new BigDecimal("100.00"));
        
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String reString = "{\"alipay_categoryService_response\":{\"status\":\"1\",\"result\":{\"category_id\":\"101\",\"category_name\":\"车生活\",\"app_total\":\"9\",\"apps\":[{\"displayapp_id\":\"1\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"20000919\",\"app_name\":\"车主平台\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\",\"sub_apps\":[{\"displayapp_id\":\"2\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"1\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_001\",\"app_name\":\"保养服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\",\"sub_apps\":[{\"displayapp_id\":\"8\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"2\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_007\",\"app_name\":\"保养服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"},{\"displayapp_id\":\"9\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"2\",\"displayapp_status\":\"1\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_008\",\"app_name\":\"洗车服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"},{\"displayapp_id\":\"10\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"2\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_009\",\"app_name\":\"美容服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"}]},{\"displayapp_id\":\"3\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"1\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_002\",\"app_name\":\"加油服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"},{\"displayapp_id\":\"4\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"1\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_003\",\"app_name\":\"停车服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"},{\"displayapp_id\":\"5\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"1\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_004\",\"app_name\":\"违章缴罚服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"},{\"displayapp_id\":\"6\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"1\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_005\",\"app_name\":\"车险服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"},{\"displayapp_id\":\"7\",\"displayapp_name\":\"\",\"displayapp_parentId\":\"1\",\"displayapp_status\":\"1\",\"is_hot\":\"\",\"sort_id\":\"\",\"action_url\":\"\",\"app_memo\":\"\",\"app_id\":\"CAR_006\",\"app_name\":\"代驾服务\",\"app_status\":\"1\",\"app_url\":\"\",\"app_logo\":\"\"}]}]}}}";
        //        String reString2 = "{\"alipay_categoryService_response\":{\"status\":\"0\",\"result\":\"具体错误原因\"}}";
        //        JSONObject jObject = JSONObject.parseObject(reString);
        //        JSONObject jObject2 = JSONObject.parseObject(reString2);
        Map<String, JSONObject> map = JSONObject.parseObject(reString, java.util.Map.class);
        //Map<String, String> map2 = JSONObject.parseObject(reString2, java.util.Map.class);
        Object status = map.get("alipay_categoryService_response").get("status");
        JSONObject alipay_categoryService_response = map.get("alipay_categoryService_response")
            .getJSONObject("result");
        JSONArray apps = alipay_categoryService_response.getJSONArray("apps");
        JSONArray sub_apps = apps.getJSONObject(0).getJSONArray("sub_apps");
        for (int i = 0; i < sub_apps.size(); i++) {
            String appStr = sub_apps.getString(i);
            Map<String, String> appMap = JSONObject.parseObject(appStr, java.util.Map.class);
            list.add(appMap);
        }
        //        Object object = jObject.get("alipay_categoryService_response");
        //        Object object2 = jObject2.get("alipay_categoryService_response");
        System.out.println(alipay_categoryService_response);
    }

}
