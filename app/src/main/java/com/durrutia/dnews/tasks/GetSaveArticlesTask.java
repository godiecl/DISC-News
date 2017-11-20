package com.durrutia.dnews.tasks;

import android.os.AsyncTask;

import com.durrutia.dnews.controller.ArticleController;
import com.durrutia.dnews.dao.AppDatabase;
import com.durrutia.dnews.model.Article;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public final class GetSaveArticlesTask extends AsyncTask<GetSaveArticlesTask.TaskListener, Void, GetSaveArticlesTask.TaskListener> {

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param taskListeners The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected TaskListener doInBackground(TaskListener... taskListeners) {

        // Getting from Internet
        final List<Article> articles = getArticles();

        // Saving in database
        if (articles != null && articles.size() != 0) {

            // Cronometro
            final StopWatch stopWatch = StopWatch.createStarted();

            log.debug("Saving models in database ..");
            FastStoreModelTransaction<Article> fastStoreModelTransaction = FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(Article.class))
                    .addAll(articles).build();

            fastStoreModelTransaction.execute(FlowManager.getWritableDatabase(AppDatabase.class));

            log.debug("Saved {} articles in {}.", articles.size(), stopWatch);

        }

        // Return the listener!
        if (taskListeners != null && taskListeners.length > 0) {
            return taskListeners[0];
        }
        return null;

    }

    /**
     * @return the {@link List} of {@link Article}.
     */
    private List<Article> getArticles() {

        // FIXME: Sera atributo de la clase?
        final ArticleController articleController = new ArticleController();

        try {
            // Obtengo los articles desde internet via el controlador
            return articleController.getArticles("techcrunch,ars-technica,engadget,buzzfeed,wired");
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param taskListener The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(final TaskListener taskListener) {
        if (taskListener != null) {
            taskListener.taskFinished();
        }
    }

    /**
     *
     */
    public interface TaskListener {

        /**
         *
         */
        void taskFinished();

    }

}
