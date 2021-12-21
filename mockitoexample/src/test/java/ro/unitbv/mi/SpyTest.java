package ro.unitbv.mi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class SpyTest {
	
	@Spy
	List<String> list = new ArrayList<String>();
	
	@Test
	void testListIsCorrect() {	
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			list.get(0);
		});		
		
		list.add("10");
		assertEquals("10", list.get(0));		
		
		when(list.get(0)).thenReturn("20");
		assertEquals("20", list.get(0));
	}	
}
