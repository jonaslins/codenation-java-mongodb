package challenge;

public interface RecipeRepositoryCustom {
    void addLike(String id, String userId);

    void removeLike(String id, String userId);

    Recipe addComment(String id, RecipeComment recipeComment);

    void updateComment(String id, String commentId, RecipeComment recipeComment);

    void deleteComment(String id, String commentId);
}
