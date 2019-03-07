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
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Andrey Kulikov on 20/03/16.
 */
class ListFragment : Fragment() {

    private var mSampleProvider: SampleListProvider? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        view.layoutManager = LinearLayoutManager(container!!.context)
        view.adapter = Adapter()
        return view
    }

    fun setSampleListListener(sampleSelectedListener: SampleListProvider) {
        mSampleProvider = sampleSelectedListener
    }

    private inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun getItemCount(): Int {
            return mSampleProvider?.sampleCount() ?: 0
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.list_item, parent, false) as TextView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = mSampleProvider?.getTitleForPosition(position)
            holder.title.setOnClickListener { mSampleProvider?.onSampleSelected(position) }
        }
    }

    private inner class ViewHolder(textView: TextView) : RecyclerView.ViewHolder(textView) {

        val title: TextView
            get() = itemView as TextView
    }

    interface SampleListProvider {

        fun sampleCount(): Int
        fun onSampleSelected(index: Int)

        fun getTitleForPosition(index: Int): String
    }
}
