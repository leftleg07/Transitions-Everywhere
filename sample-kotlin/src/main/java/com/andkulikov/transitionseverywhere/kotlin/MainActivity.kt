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

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), ListFragment.SampleListProvider {
    override fun sampleCount(): Int {
        return 13
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listFragment = ListFragment()
        listFragment.setSampleListListener(this)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ScenesSample())
                .commit()
    }

    override fun onSampleSelected(index: Int) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                        R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.container, createFragmentForPosition(index)!!)
                .addToBackStack(index.toString())
                .commit()
    }

    override fun getTitleForPosition(index: Int): String {
        when (index) {
            0 -> return "Simple animations with AutoTransition"
            1 -> return "Interpolator, duration, start delay"
            2 -> return "Path motion"
            3 -> return "Slide transition"
            4 -> return "Scale transition"
            5 -> return "Explode transition and epicenter"
            6 -> return "Transition names"
            7 -> return "ChangeImageTransform transition"
            8 -> return "Recolor transition"
            9 -> return "Rotate transition"
            10 -> return "Change text transition"
            11 -> return "Custom transition"
            12 -> return "Scene to scene transitions"
        }
        return ""
    }

    private fun createFragmentForPosition(index: Int): Fragment? {
        when (index) {

        }//            case 0: return new AutoTransitionSample();
        //            case 1: return new InterpolatorDurationStartDelaySample();
        //            case 2: return new PathMotionSample();
        //            case 3: return new SlideSample();
        //            case 4: return new ScaleSample();
        //            case 5: return new ExplodeAndEpicenterExample();
        //            case 6: return new TransitionNameSample();
        //            case 7: return new ImageTransformSample();
        //            case 8: return new RecolorSample();
        //            case 9: return new RotateSample();
        //            case 10: return new ChangeTextSample();
        //            case 11: return new CustomTransitionSample();
        //            case 12: return new ScenesSample();
        return null
    }

}
