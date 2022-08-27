package com.example.caloriecounter.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.repository.api.FoodService
import com.example.caloriecounter.utils.ApiUtils.MAX_RESULTS
import com.example.caloriecounter.utils.ApiUtils.START_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class RecipePagingSource(
    private val query: String,
    private val apiService: FoodService,
    private val apiKey: String
) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val pageIndex = params.key ?: START_PAGE_INDEX
        return try {
            val response = apiService.getRecipeByName(query, apiKey, pageIndex * 10)
            val recipeList = response.recipe
            val nextKey =
                if (recipeList.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / MAX_RESULTS)
                }
            LoadResult.Page(
                data = recipeList,
                prevKey = if (pageIndex == START_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}