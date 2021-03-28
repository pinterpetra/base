package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    //TrainSensor sensor;
    TrainSensorImpl tsensori;
    TrainController tcontroller;
    TrainUser tuser;


    @Before
    public void before() {
        //sensor = new TrainSensorImpl(null, null);
        tuser = mock(TrainUser.class);
        tcontroller = mock(TrainController.class);
        tsensori = new TrainSensorImpl(tcontroller, tuser);
    }

    /*@Test
    public void ThisIsAnExampleTestStub() {
        Assert.assertEquals(sensor.getSpeedLimit(), 5);
    }*/

    @Test
    public void Test1_speedlimit_more_than_500(){
        tsensori.overrideSpeedLimit(501);
        verify(tuser, times(1)).setAlarmState(true);
    }

    @Test
    public void Test2_speedlimit_less_than_0(){
        tsensori.overrideSpeedLimit(-1);
        verify(tuser, times(2)).setAlarmState(true);
    }

    @Test
    public void Test3_speedlimit_more_than_50percent_less(){
        tsensori.overrideSpeedLimit(tcontroller.getReferenceSpeed()/3);
        verify(tuser, times(1)).setAlarmState(true);
    }

    @Test
    public void Test4_speedlimit_is_correct(){
        tsensori.overrideSpeedLimit((tcontroller.getReferenceSpeed()+2)/2);
        verify(tuser, times(1)).setAlarmState(false);
    }
}
