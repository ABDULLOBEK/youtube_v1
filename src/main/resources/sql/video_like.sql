create or replace function video_like_function()
    returns trigger as
$$
begin
    if tg_op = 'insert' then
        if new.has_like = 'LIKE' then
            update video v
            set like_count = like_count + 1
            where v.id = new.video_id;
        elseif new.has_like = 'DISLIKE' then
            update video v
            set dislike_count = dislike_count + 1
            where v.id = new.video_id;
        end if;
    elseif tg_op = 'update' then
        if new.has_like = 'LIKE' and old.has_like = 'DISLIKE' then
            update video v
            set like_count    = like_count + 1,
                dislike_count = dislike_count - 1
            where v.id = new.video_id;
        elseif new.has_like = 'DISLIKE' and old.has_like = 'LIKE' then
            update video v
            set dislike_count = dislike_count + 1,
                like_count    = like_count - 1
            where v.id = new.video_id;
        elseif new.has_like = 'DISLIKE' and old.has_like = 'DISLIKE' then
            update video v
            set dislike_count = dislike_count - 1
            where v.id = new.video_id;
        elseif new.has_like = 'LIKE' and old.has_like = 'LIKE' then
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
