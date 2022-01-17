package es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper

import es.atrujillo.sample.funwalletms.model.ResponseMetadata
import es.atrujillo.sample.funwalletms.model.ResponseMetadataPagination
import java.time.OffsetDateTime

class MapperMockUtility {
    companion object {
        fun metadataMockCreator() : ResponseMetadata {
            val responseMetadata = ResponseMetadata()
            responseMetadata.responseTime = OffsetDateTime.now()
            responseMetadata.pagination = ResponseMetadataPagination()
            return responseMetadata
        }
    }
}