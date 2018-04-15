package bankzworld.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import bankzworld.com.R;
import bankzworld.com.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userLists;
    private int lastPosition = -1;

    public UserAdapter(List<User> userLists) {
        this.userLists = userLists;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        User userList = userLists.get(position);

        Context context = holder.circleImageView.getContext();

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up
                        : R.anim.down);
        holder.itemView.startAnimation(animation);

        lastPosition = position;

        holder.userName.setText(userList.getLogin());
        holder.userUrl.setText(userList.getHtmlUrl());

        Glide.with(context).load(userList.getAvatarUrl())
                .thumbnail(0.10f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView circleImageView;
        TextView userName, userUrl;

        public UserViewHolder(View view) {
            super(view);

            circleImageView = (CircleImageView) view.findViewById(R.id.user_image);
            userName = (TextView) view.findViewById(R.id.user_name);
            userUrl = (TextView) view.findViewById(R.id.user_url);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            User user = userLists.get(getLayoutPosition());
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(user.getHtmlUrl()));
            context.startActivity(i);
        }
    }
}
