package org.sdelaysam.carprice.util.data

import kotlinx.serialization.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import timber.log.Timber

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */


@Serializer(forClass = DateTime::class)
object DateTimeSerializer : KSerializer<DateTime?> {

    private val formatter = ISODateTimeFormat.dateTimeParser()

    override val descriptor: SerialDescriptor =
        PrimitiveDescriptor("DateTimeSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DateTime?) {
        value?.let {
            encoder.encodeString(it.toString(formatter))
        }
    }

    override fun deserialize(decoder: Decoder): DateTime? {
        return try {
            formatter.parseDateTime(decoder.decodeString())
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
}