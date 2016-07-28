package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.oe.industrydata.common.service.facade.model.result.CityModelResult;
import com.alipay.oe.industrydata.common.service.facade.model.rpc.Result;
import com.alipay.oe.industrydata.common.service.facade.model.rpc.ResultContent;

public class TestUrl {

    private static final Logger logger = LoggerFactory.getLogger("vbizplatform");

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Result<CityModelResult> result = new Result<CityModelResult>();
            result.setCode(10000);
            result.setMsg("成功");
            result.setSubCode("00000");
            result.setSubMsg("成功");
            ResultContent<CityModelResult> resultContent = new ResultContent<CityModelResult>();
            CityModelResult cityModelResult = new CityModelResult();
            cityModelResult.setCityCode("010001");
            cityModelResult.setCityName("北京");
            resultContent.setContent(cityModelResult);
            result.setResultContent(resultContent);
            //logger.info("返回结果：{}", BeanUtil.transBean2Map(result));
            logger.info("返回结果：{}", result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
