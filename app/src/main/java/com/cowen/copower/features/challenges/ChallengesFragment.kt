package com.cowen.copower.features.challenges

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cowen.copower.CopowerApp
import com.cowen.copower.R
import com.cowen.copower.commons.CopowerChallenges
import com.cowen.copower.commons.InfiniteScrollListener
import com.cowen.copower.commons.ViewModelFactory
import com.cowen.copower.commons.extensions.androidLazy
import com.cowen.copower.commons.extensions.getViewModel
import com.cowen.copower.commons.extensions.inflate
import com.cowen.copower.features.challenges.adapter.ChallengesAdapter
import com.cowen.copower.features.challenges.adapter.ChallengesDelegateAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kotlinx.android.synthetic.main.challenges_fragment.*

class ChallengesFragment : Fragment(), ChallengesDelegateAdapter.onViewSelectedListener {
    override fun onItemSelected(url: String?){
        if(url.isNullOrEmpty()){
            Snackbar.make(challenges_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        }else{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    companion object {
        private const val KEY_CHALLENGES = "challenges"
    }

    private var copowerChallenges: CopowerChallenges? = null
    private val challengesAdapter by androidLazy { ChallengesAdapter(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ChallengesViewModel>
    private val challengesViewModel by androidLazy {
        getViewModel<ChallengesViewModel>(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CopowerApp.challengeComponent.inject(this)

        challengesViewModel.challengesState.observe(this, Observer<ChallengesState> {
            manageState(it)
        })
    }

    private fun manageState(challengesState: ChallengesState?) {
        val state = challengesState ?: return
        when (state) {
            is ChallengesState.Success -> {
                copowerChallenges = state.copowerChallenges
                challengesAdapter.addChallenges(state.copowerChallenges.challenges ?: emptyList())
            }
            is ChallengesState.Error -> {
                Snackbar.make(challenges_list, state.message.orEmpty(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY") { requestChallenges() }
                    .show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.challenges_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        challenges_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestChallenges() }, linearLayout))
        }

        challenges_list.adapter = challengesAdapter

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_CHALLENGES)) {
            copowerChallenges = savedInstanceState.get(KEY_CHALLENGES) as CopowerChallenges
            challengesAdapter.clearAndAddChallenges(copowerChallenges!!.challenges ?: emptyList())
        } else {
            requestChallenges()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val challenges = challengesAdapter.getChallenges()
        if (challenges.isNotEmpty()) {
            outState.putParcelable(KEY_CHALLENGES, copowerChallenges?.copy(challenges = challenges))
        }
    }

    private fun requestChallenges() {
        /**
         * first time will send empty string for 'after' parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the 'after' param.
         */
        challengesViewModel.fetchChallenges("")
    }
}