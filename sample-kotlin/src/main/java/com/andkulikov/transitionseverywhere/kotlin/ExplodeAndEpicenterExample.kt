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

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.transitionseverywhere.extra.Scale

/**
 * Created by Andrey Kulikov on 25/03/16.
 */
class ExplodeAndEpicenterExample : Fragment() {

    private var mRecyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRecyclerView = RecyclerView(container!!.context)
        mRecyclerView!!.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        mRecyclerView!!.layoutManager = GridLayoutManager(container.context, 4)
        mRecyclerView!!.adapter = Adapter()
        return mRecyclerView
    }

    private fun letsExplodeIt(clickedView: View) {
        // save rect of view in screen coordinated
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)

        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.excludeTarget(clickedView, true)
        val set = TransitionSet()
                .addTransition(explode)
                .addTransition(Scale(0.2f).addTarget(clickedView))
                .addListener(object : TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition) {
                        transition.removeListener(this)
                        activity!!.onBackPressed()
                    }
                })
        TransitionManager.beginDelayedTransition(mRecyclerView!!, set)

        // remove all views from Recycler View
        mRecyclerView!!.adapter = null
    }

    private inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.explode_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener { clickedView -> letsExplodeIt(clickedView) }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
