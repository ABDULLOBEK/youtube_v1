create or replace function video_like_function()
    returns trigger as
$$
begin
    if tg_op = 'insert' then
        if new.has_liked = 'LIKE' then
            update video v
            set like_count = like_count + 1
            where v.id = new.video_id;
        elseif new.has_liked = 'DISLIKE' then
            update video v
            set dislike_count = dislike_count + 1
            where v.id = new.video_id;
        end if;
    elseif tg_op = 'update' then
        if new.has_liked = 'LIKE' and old.has_liked = 'DISLIKE' then
            update video v
            set like_count = like_count + 1
            where v.id = new.video_id;
            update video v
            set dislike_count = dislike_count - 1
            where v.id = new.video_id;
        elseif new.has_liked = 'DISLIKE' and old.has_liked = 'LIKE' then
            update video v
            set like_count = like_count - 1
            where v.id = new.video_id;
            update video v
            set dislike_count = dislike_count + 1
            where v.id = new.video_id;
        elseif new.has_liked = 'DISLIKE' and old.has_liked = 'DISLIKE' then
            update video v
            set dislike_count = dislike_count - 1
            where v.id = new.video_id;
        elseif new.has_liked = 'LIKE' and old.has_liked = 'LIKE' then
            update video v
            set like_count = like_count - 1
            where v.id = new.video_id;
        end if;
    end if;
    return new;
end;
$$ language plpgsql;

create or replace trigger video_like_trigger
    before insert or update
    on video_like
    for each row
execute procedure video_like_function();
