package es.atrujillo.sample.funwalletms.helpers

import org.mockito.Mockito

class MockitoKotlinHelper {

    companion object {
         fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
    }

}