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
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager

/**
 * Created by Andrey Kulikov on 24/03/16.
 */
class PathMotionSample : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_path, container, false)

        val transitionsContainer = view.findViewById<ViewGroup>(R.id.transitions_container)
        val button = transitionsContainer.findViewById<View>(R.id.button)

        button.setOnClickListener(object : View.OnClickListener {

            internal var mToRightAnimation: Boolean = false

            override fun onClick(v: View) {
                val changeBounds = ChangeBounds()
//                changeBounds.setPathMotion(ArcMotion())
                changeBounds.duration = 500
                TransitionManager.beginDelayedTransition(transitionsContainer, changeBounds)

                mToRightAnimation = !mToRightAnimation
                val params = button.layoutParams as FrameLayout.LayoutParams
                params.gravity = if (mToRightAnimation)
                    Gravity.RIGHT or Gravity.BOTTOM
                else
                    Gravity.LEFT or Gravity.TOP
                button.layoutParams = params
            }

        })

        return view
    }


}
