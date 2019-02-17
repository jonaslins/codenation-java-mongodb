package challenge;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe update(String id, Recipe recipe) {
		Recipe recipeToUpdate = recipeRepository.findById(id)
				.orElseThrow(RuntimeException::new);
		recipeToUpdate.setTitle(recipe.getTitle());
		recipeToUpdate.setDescription(recipe.getDescription());
		recipeToUpdate.setIngredients(recipe.getIngredients());
		return recipeRepository.save(recipeToUpdate);
	}

	@Override
	public void delete(String id) {
		if(!recipeRepository.existsById(id)){
			throw new RuntimeException(); //TODO refactor
		}
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe get(String id) {
		return recipeRepository.findById(id).orElseThrow(RuntimeException::new);//TODO refactor
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return recipeRepository.findByIngredientsOrderByTitleAsc(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return recipeRepository.findByTitleContainingOrDescriptionContainingAllIgnoreCaseOrderByTitleAsc(search, search);
	}

	@Override
	public void like(String id, String userId) {
		recipeRepository.addLike(id, userId);
	}

	@Override
	public void unlike(String id, String userId) {
		recipeRepository.removeLike(id, userId);
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment recipeComment) {
		recipeComment.setId(ObjectId.get().toString());
		recipeRepository.addComment(id, recipeComment);
		return recipeComment;

	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		recipeRepository.updateComment(id, commentId, comment);
	}

	@Override
	public void deleteComment(String id, String commentId) {
		recipeRepository.deleteComment(id, commentId);
	}

}
