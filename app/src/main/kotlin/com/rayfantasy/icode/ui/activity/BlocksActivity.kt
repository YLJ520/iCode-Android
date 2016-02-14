package com.rayfantasy.icode.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator
import com.rayfantasy.icode.R
import com.rayfantasy.icode.databinding.ActivityBlocksBinding
import com.rayfantasy.icode.model.ICodeTheme
import com.rayfantasy.icode.postutil.PostUtil
import com.rayfantasy.icode.postutil.bean.CodeGood
import com.rayfantasy.icode.postutil.extension.fromJson
import com.rayfantasy.icode.ui.adapter.BlockAdapter
import kotlinx.android.synthetic.main.activity_blocks.*
import kotlinx.android.synthetic.main.content_blocks.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class BlocksActivity : ActivityBindingStatus() {
    private lateinit var codeGood: CodeGood
    private lateinit var binding: ActivityBlocksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blocks)
        binding.theme = ICodeTheme
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        codeGood = intent.getSerializableExtra("codeGood") as CodeGood
        title = codeGood.title
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@BlocksActivity)
            itemAnimator = RefactoredDefaultItemAnimator()
        }

        codeGood.content?.let { recyclerView.adapter = BlockAdapter(this, codeGood, PostUtil.gson.fromJson(codeGood.content)) }

        PostUtil.loadCodeContent(codeGood.id!!,
                {
                    with(codeGood) {
                        content = it
                        save()
                    }
                    if (recyclerView.adapter == null)
                        recyclerView.adapter = BlockAdapter(this, codeGood, PostUtil.gson.fromJson(codeGood.content))
                    else {
                        (recyclerView.adapter as BlockAdapter).blocks = PostUtil.gson.fromJson(codeGood.content)
                        recyclerView.adapter.notifyDataSetChanged()
                    }
                }, { t, rc ->
            toast("rc = $rc")
            t.printStackTrace()
        })
        block_fab.onClick { startActivity<ReplyActivity>("id" to codeGood.id) }
    }

}
