package junit.org.rapidpm.demo.jaxenter.blog0002;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.demo.jaxenter.blog0002.demo.DemoLogic;
import org.rapidpm.demo.jaxenter.blog0002.demo.demologic.DemoContext;
import org.rapidpm.demo.jaxenter.blog0002.demo.demologic.DemoLogicContext;

/**
 * User: Sven Ruppert
 * Date: 21.10.13
 * Time: 11:14
 */
@RunWith(Arquillian.class)
public class ContextResolverTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm.demo.jaxenter")
                .addPackages(true, "junit.org.rapidpm.demo.jaxenter")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private @Inject @DemoLogicContext Instance<DemoLogic> logicInstance;
    private @Inject DemoContext context;

    @Test
    public void testContextResolver001() throws Exception {

        final String resultFromDoIt001 = logicInstance.get().doIt();
        Assert.assertNotNull(resultFromDoIt001);
        Assert.assertFalse(resultFromDoIt001.isEmpty());
        Assert.assertTrue(resultFromDoIt001.endsWith("-B"));

        context.toggle = ! context.toggle;

        final String resultFromDoIt002 = logicInstance.get().doIt();
        Assert.assertNotNull(resultFromDoIt002);
        Assert.assertFalse(resultFromDoIt002.isEmpty());
        Assert.assertTrue(resultFromDoIt002.endsWith("-A"));

    }


}
