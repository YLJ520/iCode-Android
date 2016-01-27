/*
 * Copyright 2015 Alex Zhang aka. ztc1997
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rayfantasy.icode.ui

import android.os.Bundle
import com.rayfantasy.icode.R
import com.rayfantasy.icode.postutil.CodeGood
import kotlinx.android.synthetic.main.content_view_code.*

class ViewCodeActivity : BaseActivity() {
    private lateinit var codeGood: CodeGood

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_code)
        tv_code.isEnabled = false
        tv_code.isFocusable = false

        codeGood = intent.getSerializableExtra("codeGood") as CodeGood
        title = codeGood.title

        tv_code.setText(codeGood.content)
        view_code_tv_sub.text = codeGood.subtitle
    }


}
