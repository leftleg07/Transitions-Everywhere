/*
 * Copyright (C) 2016 Andrey Kulikov (andkulikov@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.andkulikov.transitionseverywhere.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.transitionseverywhere.extra.Scale
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

/**
 * Created by Andrey Kulikov on 24/03/16.
 */
class ScaleSample : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scale, container, false)

        val transitionsContainer = view.findViewById<ViewGroup>(R.id.transitions_container)
        val text1 = transitionsContainer.findViewById<TextView>(R.id.text1)


        transitionsContainer.findViewById<View>(R.id.button1).setOnClickListener{
            val transition = Fade()
            transition.duration = 4000
            TransitionManager.beginDelayedTransition(transitionsContainer, transition)
            with(text1) {
                val visible = visibility == View.VISIBLE
                visibility = if (visible) View.INVISIBLE else View.VISIBLE
            }
        }

        val text2 = transitionsContainer.findViewById<TextView>(R.id.text2)

        transitionsContainer.findViewById<View>(R.id.button2).setOnClickListener {
            with(text2) {
                val visible = visibility == View.VISIBLE
                val set = TransitionSet()
                        .addTransition(Scale(0.7f))
                        .addTransition(Fade())
                        .setInterpolator(LinearOutSlowInInterpolator())
//                        .setInterpolator(if (visible) LinearOutSlowInInterpolator() else FastOutLinearInInterpolator())
                TransitionManager.beginDelayedTransition(transitionsContainer, set)
                visibility = if (visible) View.INVISIBLE else View.VISIBLE
            }
        }
        return view
    }

}
