package velocity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityTest {

    public static void main(String[] args) throws Exception {
        Properties pros = new Properties();
        pros.load(new FileInputStream(new File("src/test/resources/velocity.properties")));
        //初始化并取得Velocity引擎
        VelocityEngine ve = new VelocityEngine();
        ve.init(pros);
        //取得velocity的模版
        Template t = ve.getTemplate("topbars.vm");
        //取得velocity的上下文context 
        VelocityContext context = new VelocityContext();
        StringWriter write = new StringWriter();
        t.merge(context, write);
        System.out.println(write.toString());
        File file = new File("d://test.jsp");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos, true, "UTF-8");//这里我们就可以设置编码了
        ps.print(write.toString());
        ps.flush();
        ps.close();
    }
}
