package com.durrutia.dnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.durrutia.dnews.R;
import com.durrutia.dnews.model.Article;
import com.facebook.drawee.view.SimpleDraweeView;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.ocpsoft.prettytime.PrettyTime;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public final class ArticleDBFlowAdapter extends BaseAdapter {

    /**
     * Formateador de fecha
     */
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    /**
     * FlowDB
     */
    private final FlowCursorList<Article> flowCursorList;

    /**
     * Context
     */
    private final Context context;

    /**
     *
     * @param context to get the {@link LayoutInflater}.
     */
    public ArticleDBFlowAdapter(@NonNull final Context context) {

        this.context = context;

        this.flowCursorList = new FlowCursorList.Builder<>(SQLite.select().from(Article.class))
                .build();

        log.debug("Size: {}", this.flowCursorList.getCount());

    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
    @Override
    public void notifyDataSetChanged() {

        // Fresh the SQL
        this.flowCursorList.refresh();

        // Notify the list
        super.notifyDataSetChanged();

    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return (int) flowCursorList.getCount();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Article getItem(int position) {
        return flowCursorList.getItem(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        final View view;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_article, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Article article = this.getItem(position);
        if (article != null) {

            viewHolder.title.setText(article.getTitle());
            viewHolder.description.setText(article.getDescription());

            viewHolder.date.setText(PRETTY_TIME.format(article.getPublishedAtDateTime().toDate()));
            viewHolder.source.setText(article.getSource().getName());

            viewHolder.image.setImageURI(article.getUrlToImage());

        }

        return view;
    }

    /**
     * Viewholder pattern
     */
    private static class ViewHolder {

        TextView title;
        TextView description;
        TextView date;
        SimpleDraweeView image;
        TextView source;

        ViewHolder(final View view) {
            this.title = view.findViewById(R.id.ra_title);
            this.description = view.findViewById(R.id.ra_description);
            this.date = view.findViewById(R.id.ra_date);
            this.image = view.findViewById(R.id.ra_image);
            this.source = view.findViewById(R.id.ra_source);
        }

    }
}
