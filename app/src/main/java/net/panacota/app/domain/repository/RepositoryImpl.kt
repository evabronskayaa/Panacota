package net.panacota.app.domain.repository

import androidx.annotation.IntRange
import net.panacota.app.domain.api.RecipesApi
import net.panacota.app.domain.data.ListRecipe
import net.panacota.app.domain.data.MealType
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val recipesApi: RecipesApi) : Repository {
    override suspend fun getRecipes(): Response<ListRecipe> = recipesApi.complexSearch()
    override suspend fun getRecipesByType(mealType: MealType, @IntRange(1, 100) limit: Int): Response<ListRecipe> =
        recipesApi.complexSearch(
            mapOf(
                "type" to mealType.toString(),
                "addRecipeInformation" to "true",
                "fillIngredients" to "true",
                "limit" to limit.toString()
            )
        )
}