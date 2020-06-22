package org.sdelaysam.carprice.data.storage

import android.content.Context

/**
 * Created on 6/22/20.
 * @author sdelaysam
 */

interface AppStorage {
    var makeId: String?
    var modelId: String?
    var subModelId: String?
    var year: Int?
}

class DefaultAppStorage(
    context: Context
): AppStorage {

    private val prefs = context.getSharedPreferences("AppStorage", Context.MODE_PRIVATE)

    companion object {
        private const val KeyMakeId = "makeId"
        private const val KeyModelId = "modelId"
        private const val KeySubModelId = "subModelId"
        private const val KeyYear = "year"
    }

    override var makeId: String? = prefs.getString(KeyMakeId, null)
        set(value) {
            if (field != value) {
                prefs.edit().putString(KeyMakeId, value).apply()
                field = value
            }
        }

    override var modelId: String? = prefs.getString(KeyModelId, null)
        set(value) {
            if (field != value) {
                prefs.edit().putString(KeyModelId, value).apply()
                field = value
            }
        }

    override var subModelId: String? = prefs.getString(KeySubModelId, null)
        set(value) {
            if (field != value) {
                prefs.edit().putString(KeySubModelId, value).apply()
                field = value
            }
        }

    override var year: Int? = prefs.getInt(KeyYear, -1).takeIf { it > 0 }
        set(value) {
            if (field != value) {
                if (value != null) {
                    prefs.edit().putInt(KeyYear, value).apply()
                } else {
                    prefs.edit().remove(KeyYear).apply()
                }
                field = value
            }
        }
}