package ai.doma.feature_miniapps.domain

import ai.doma.miniappdemo.data.MiniappRepository
import ai.doma.miniappdemo.data.TEST_MINIAPP_URL
import android.content.Context
import com.dwsh.storonnik.DI.FeatureScope
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


const val MINIAPP_SERVER_AUTH_ID = "serverAuthTest"
const val MINIAPP_SERVER_AUTH_BY_URL_ID = "serverAuthByUrlTest"

@FeatureScope
class MiniappInteractor @Inject constructor(val context: Context, val miniappRepository: MiniappRepository) {

    fun getOrDownloadMiniapp(miniappId: String) = flow {
        val localAppFile = MiniappRepository.getMiniapp(context, miniappId)
        if(true /*!localAppFile.exists()*/){
            miniappRepository.downloadMiniappFromUrl(miniappId, TEST_MINIAPP_URL)
            val newLocalAppFile = MiniappRepository.getMiniapp(context, miniappId)
            emit(newLocalAppFile)
        } else {
            emit(localAppFile)
        }
    }

}