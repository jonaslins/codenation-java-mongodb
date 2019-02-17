package challenge;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collections;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Repository
public class RecipeRepositoryCustomImpl implements RecipeRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void addLike(String id, String userId) {
        Query query = new Query(where("id").is(id));
        Update update = new Update().push("likes", userId);
        Recipe teste = mongoTemplate.findAndModify(query, update, Recipe.class);
    }

    @Override
    public void removeLike(String id, String userId) {
        Query query = new Query(where("id").is(id));
        Update update = new Update().pull("likes", userId);
        Recipe teste = mongoTemplate.findAndModify(query, update, Recipe.class);
    }

    @Override
    public Recipe addComment(String id, RecipeComment recipeComment) {
        Query query = new Query(where("id").is(id));
        Update update = new Update().push("comments", recipeComment);
        Recipe teste = mongoTemplate.findAndModify(query, update, Recipe.class);
        return teste;
    }

    @Override
    public void updateComment(String id, String commentId, RecipeComment recipeComment) {
        Query query = new Query(where("id").is(id).and("comments.id").is(commentId));
        Update update = new Update().set("comments.$.comment", recipeComment.getComment());
        UpdateResult teste = mongoTemplate.updateFirst(query, update, Recipe.class);
        System.out.println(teste);
    }

    @Override
    public void deleteComment(String id, String commentId) {
        Query query = new Query(where("id").is(id).and("comments.id").is(commentId));
        Update update = new Update().pull("comments", new BasicDBObject("id", commentId));
        UpdateResult teste = mongoTemplate.updateFirst(query, update, Recipe.class);
        System.out.println(teste);
    }

}
