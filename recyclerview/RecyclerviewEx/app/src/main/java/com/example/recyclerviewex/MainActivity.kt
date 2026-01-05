package com.example.recyclerviewex

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val items = mutableListOf<NameItem>()
    val snapHelper = PagerSnapHelper()
    private lateinit var adapter: NameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setRecyclerView()

        binding.btnAdd.setOnClickListener {
            items.add(NameItem("맹구"))
            adapter.submitList(items.toList())
        }


    }

    fun setRecyclerView() {

        snapHelper.attachToRecyclerView(binding.recyclerview)
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val itemWidth = (screenWidth * 0.8f).toInt()
        binding.recyclerview.clipToPadding = false //padding 영역에도 아이템이 보이도록
        val sidePadding = ((screenWidth - itemWidth) / 2f).toInt()
        Log.e("JH", "sidePadding >> $sidePadding")
        binding.recyclerview.setPadding(sidePadding, 0, sidePadding, 0)
        val adapter = NameAdapter(itemWidth)
        binding.recyclerview.adapter = adapter
        items.addAll(
            listOf(
                NameItem("철수"),
                NameItem("짱구"),
                NameItem("맹구"),
                NameItem("훈이"),
                NameItem("짱아")
            )
        )
        binding.recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter.submitList(items)
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val centerX = recyclerView.width / 2f // 중앙 x좌표 계산

                for (i in 0 until recyclerView.childCount) { // 현재 화면의 아이템 개수
                    val child = recyclerView.getChildAt(i) ?: continue

                    val childCenterX = (child.left + child.right) / 2f
                    val distanceFromCenter = kotlin.math.abs(centerX - childCenterX)

                    // 0.0 ~ 1.0 사이 비율로 정규화 (거리 비율)
                    val maxDistance = recyclerView.width / 2f
                    val ratio = (distanceFromCenter / maxDistance).coerceIn(0f, 1f)

                    // 가운데(0)에 가까울수록 scale 1.0, 멀어질수록 0.85
                    val scale = 0.85f + (1f - ratio) * 0.15f

                    child.scaleX = scale
                    child.scaleY = scale
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

//                // 이 부분은 자동 스크롤 / 인디케이터용 현재 포지션 업데이트에 활용
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    val view = snapHelper.findSnapView(layoutManager)
//                    val pos = view?.let { layoutManager.getPosition(it) } ?: RecyclerView.NO_POSITION

//                    if (pos != RecyclerView.NO_POSITION) {
//                        currentPosition = pos
//                        // 나중에 indicator.update(currentPosition) 같은 형태로 사용
//                    }
//                }
            }
        })
        binding.recyclerview.addItemDecoration(object :
            RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val density = resources.displayMetrics.density
                val dp10 = (10 * density).toInt()
                val position = parent.getChildAdapterPosition(view)
                if (position == RecyclerView.NO_POSITION) return
                val itemCount = state.itemCount
                when (position) {
                    0 -> {
                        // 첫 번째 아이템: 오른쪽만 여백
                        outRect.left = 0
                        outRect.right = dp10 / 2
                    }

                    itemCount - 1 -> {
                        // 마지막 아이템: 왼쪽만 여백
                        outRect.left = dp10 / 2
                        outRect.right = 0
                    }

                    else -> {
                        // 중간 아이템들: 양쪽에 반반 여백
                        outRect.left = dp10 / 2
                        outRect.right = dp10 / 2
                    }
                }
            }
        })
    }
}
