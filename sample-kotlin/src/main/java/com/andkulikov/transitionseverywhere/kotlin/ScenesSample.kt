package com.andkulikov.transitionseverywhere.kotlin


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.transition.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ScenesSample : Fragment() {

    private val sceneRoot by lazy { view?.findViewById(R.id.scene_root) as ViewGroup }
    private val scene1 by lazy { Scene.getSceneForLayout(sceneRoot, R.layout.scene1, context!!) }
    private val scene2 by lazy { Scene.getSceneForLayout(sceneRoot, R.layout.scene2, context!!) }
    private val scene3 by lazy { Scene.getSceneForLayout(sceneRoot, R.layout.scene3, context!!) }
    private val radioGroup by lazy { view?.findViewById(R.id.select_scene) as RadioGroup }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scenes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.select_scene_2 -> transitionToScene2()
                R.id.select_scene_3 -> transitionToScene3()
                else -> transitionToScene1()
            }
        }

        transitionToScene1()
    }

    private fun transitionToScene1() {
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.scene2_transition)
        TransitionManager.go(scene1, transition)
        sceneRoot.findViewById<View>(R.id.transition_oval).setOnClickListener { Toast.makeText(context, "oval scene1", Toast.LENGTH_SHORT).show() }
    }

    private fun transitionToScene2() {
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.scene2_transition)
        TransitionManager.go(scene2, transition)
        sceneRoot.findViewById<View>(R.id.transition_oval).setOnClickListener { Toast.makeText(context, "oval scene2", Toast.LENGTH_SHORT).show() }
    }

    private fun transitionToScene3() {
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.scene3_transition)
        TransitionManager.go(scene3, transition)
        sceneRoot.findViewById<View>(R.id.transition_oval).setOnClickListener { Toast.makeText(context, "oval scene3", Toast.LENGTH_SHORT).show() }
    }
}
