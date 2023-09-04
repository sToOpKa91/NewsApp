package com.sto_opka91.newsapp.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sto_opka91.newsapp.R
import com.sto_opka91.newsapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.Url

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val bundleArgs:DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articeArg = bundleArgs.article
        articeArg.let { article ->
            article.urlToImage.let {
                Glide.with(this).load(article.urlToImage).into(binding.iVHeaderImage)
            }
            binding.iVHeaderImage.clipToOutline = true
            binding.tVArticleDetailsTitle.text = article.title
            binding.tvDescriptionText.text = article.description
            binding.btnGoToResours.setOnClickListener {
                try {
                    Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .addCategory(Intent.CATEGORY_BROWSABLE)
                        .setData(Uri.parse(takeIf { URLUtil.isValidUrl(article.url) }
                            ?.let{
                                article.url
                            }?:"https://google.com")).let{
                                ContextCompat.startActivity(requireContext(), it,null)
                        }

                }catch(e: Exception){
                   Toast.makeText(context, "The device don't load this document", Toast.LENGTH_SHORT).show()
                }
            }
            binding.iVIconFavorite.setOnClickListener{
                viewModel.saveFavoriteArticles(article)
            }
        }
    }
}