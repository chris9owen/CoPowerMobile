package com.cowen.copower.features.challenges

import com.cowen.copower.commons.CopowerChallenges

sealed class ChallengesState {
    class Success(val copowerChallenges: CopowerChallenges) : ChallengesState()
    class Error(val message: String?) : ChallengesState()
}