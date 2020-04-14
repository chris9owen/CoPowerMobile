package com.cowen.copower.features.challenges

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cowen.copower.api.ChallengesApi
import com.cowen.copower.api.ChallengesResponse
import com.cowen.copower.commons.Challenge
import com.cowen.copower.commons.CopowerChallenges
import com.cowen.copower.commons.Logger.dt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * NewsViewModel allows you to request news from Reddit API.
 *
 * @author juancho
 */
class ChallengesViewModel @Inject constructor(
    private val api: ChallengesApi
) : ViewModel() {

    val challengesState: MutableLiveData<ChallengesState> = MutableLiveData()

    fun fetchChallenges(after: String, limit: String = "10") =
        GlobalScope.launch {
            try {
                val result = api.getChallenges(after, limit)
                val challenges = process(result)
                challengesState.postValue(ChallengesState.Success(challenges))
            } catch (e: Throwable) {
                Log.d("Copower", "Error with fetch Challenges", e)
                challengesState.postValue(ChallengesState.Error(e.message))
            }
        }

    private fun process(response: ChallengesResponse): CopowerChallenges {
        val challenges = response.data.map {
            Challenge(it.id, it.title, it.text)
        }
        return CopowerChallenges(challenges)
    }
}