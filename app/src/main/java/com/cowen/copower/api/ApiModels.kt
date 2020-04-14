package com.cowen.copower.api

class ChallengesResponse(
        val data: List<ChallengeResponse>,
        val meta: MetaResponse
)

class ChallengeResponse(
        val id: Int,
        val title: String,
        val text: String,
        val created_at: String?,
        val updated_at: String?,
        val resolved_at: String?,
        val last_comment: String?,
        val council_count: Int,
        val council_max: Int
)

class MetaResponse(
        val current_page: Int,
        val from: Int,
        val last_page: Int,
        val path: String,
        val per_page: Int,
        val to: Int,
        val total: Int
)