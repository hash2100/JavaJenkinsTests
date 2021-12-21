package ro.unitbv.mi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// https://www.vogella.com/tutorials/Mockito/article.html


@ExtendWith(MockitoExtension.class)                         
class ServiceTest {
    @Mock
    Database databaseMock;                                  

    @Test
    public void testQuery()  {

        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);  
        when(databaseMock.getUniqueId()).thenReturn(12);
        
        Service service = new Service(databaseMock);             
        boolean check = service.query("* from t");                
        assertTrue(check);
        
        assertEquals(service.toString(), "Using database with id: 12");
    }
    
    @Test
    public void testVerify(@Mock Database database)  {
        // create and configure mock
        when(database.getUniqueId()).thenReturn(43);

        // call method testing on the mock with parameter 12
        database.setUniqueId(12);
        database.getUniqueId();
        database.getUniqueId();

        // now check if method testing was called with the parameter 12
        verify(database).setUniqueId(ArgumentMatchers.eq(12));

        // was the method called twice?
        verify(database, times(2)).getUniqueId();

        // other alternatives for verifiying the number of method calls for a method
        verify(database, never()).isAvailable();
        verify(database, never()).setUniqueId(13);
        verify(database, atLeastOnce()).setUniqueId(12);
        verify(database, atLeast(2)).getUniqueId();

        // more options are
        // times(numberOfTimes)
        // atMost(numberOfTimes)
        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(database);
    }
}
