import alexggg99.mvc.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by alexggg99 on 25.11.15.
 */

public class CalcTest {

    @Mock
    private Calculator calc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testABS(){
        when(calc.ABS(-20)).thenReturn(20);
        assertEquals(20,calc.ABS(-20));
    }
}
