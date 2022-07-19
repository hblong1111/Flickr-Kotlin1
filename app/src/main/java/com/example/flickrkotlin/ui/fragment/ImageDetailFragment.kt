package com.example.flickrkotlin.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.api.ConverterAPI
import com.example.api.FlickrRetrofit
import com.example.api.model.FlickrResult
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.DetailAdapter
import com.example.flickrkotlin.databinding.FragmentImageDetailBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageDetailFragment : FragmentBase<FragmentImageDetailBinding>(), DetailAdapter.Callback {

    private lateinit var photoViewModel: PhotoViewModel

    private lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]
        adapter = DetailAdapter(photoViewModel.photos, this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_image_detail
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDescription.apply {
            setShowingLine(3)
            setShowLessTextColor(Color.BLUE)
            setShowMoreTextColor(Color.BLUE)
            addShowLessText("Show less")
            addShowMoreText("Show more")
        }
        binding.viewpager.adapter = adapter

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position != photoViewModel.positionSelect) {
                    photoViewModel.positionSelect = position
                }
                photoViewModel.positionFocus.postValue(position)


                CoroutineScope(Dispatchers.Main).launch {
                    setupInfo(photoViewModel.photos[position], position)
                }

            }

        })
        binding.viewpager.setCurrentItem(photoViewModel.positionSelect, false)


    }

    suspend fun setupInfo(photo: FlickrResult.Photos.Photo, position: Int) {
        setupPreLoading()
        bindInfoToView(checkInfoPhoto(photo), position)
    }

    private fun setupPreLoading() {
        setTextPreLoad()
        setBackgroundLoading(true)
        binding.smBottom.showShimmer(true)
    }

    private fun setTextPreLoad() {
        val txtPreload = binding.tvShare.text
        binding.tvDescription.setTextMore(txtPreload)
        binding.tvDate.text = txtPreload
        binding.tvUsername.text = txtPreload
        binding.tvTitle.text = txtPreload
        binding.tvViews.text = txtPreload
        binding.tvFavorites.text = txtPreload
        binding.tvComment.text = txtPreload

    }

    private fun setBackgroundLoading(b: Boolean) {

        if (isAdded) {
            val colorBackground = if (b) {
                R.color.white
            } else {
                android.R.color.transparent
            }
            binding.tvFavorites.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvComment.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvDescription.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvTitle.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvUsername.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvDate.background = ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvViews.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
            binding.tvShare.background =
                ContextCompat.getDrawable(requireContext(), colorBackground)
        }

    }

    private suspend fun checkInfoPhoto(photo: FlickrResult.Photos.Photo): FlickrResult.Photos.Photo? {
        try {
            if (TextUtils.isEmpty(photo.description) && TextUtils.isEmpty(photo.username) && photo.favorites == 0) {
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                    photo.id?.let {
                        ConverterAPI.getOptionCallPhotoInfo(
                            it
                        )
                    }?.let {
                        val info = FlickrRetrofit.flickrService.getInfo(it).execute()

                        if (info.isSuccessful && info.code() == 200) {
                            val infoDetail = info.body()?.photo
                            photo.username = infoDetail?.owner?.username
                            photo.date = infoDetail?.dates?.taken
                            photo.description = infoDetail?.description?.content
                            photo.views = infoDetail?.views?.replace(Regex("[^0-9]"), "")?.toInt()!!
                            photo.comments =
                                infoDetail?.comments?.content?.replace(Regex("[^0-9]"), "")
                                    ?.toInt()!!


                            val favorite = FlickrRetrofit.flickrService.getFavorite(
                                ConverterAPI.getOptionCallPhotoFavorites(
                                    photo.id!!
                                )
                            ).execute()


                            if (favorite.isSuccessful && favorite.code() == 200) {
                                try {
                                    photo.favorites = favorite.body()?.photo?.total!!
                                } catch (ex: Exception) {
                                    ex.printStackTrace()
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()

            return null
        }
        return photo
    }


    private fun bindInfoToView(photo: FlickrResult.Photos.Photo?, position: Int) {
        if (photo != null && position == binding.viewpager.currentItem) {
            binding.tvFavorites.text = photo.favorites.toString()
            binding.tvViews.text = photo.views.toString()
            binding.tvComment.text = photo.comments.toString()
            binding.tvDescription.setTextMore(Html.fromHtml(photo.description.toString()))
            binding.tvTitle.text = photo.title.toString()
            binding.tvUsername.text = photo.username.toString()
            binding.tvDate.text = photo.date.toString()

            setBackgroundLoading(false)
            binding.smBottom.hideShimmer()
        } else if (photo == null) {
            Toast.makeText(context, "Check connection!!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        photoViewModel.positionFocus.postValue(-1)
        super.onStop()

    }

    override fun onTapPhoto() {
        val visibleInfo = if (binding.infoGroup.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        binding.infoGroup.visibility = visibleInfo
    }

}