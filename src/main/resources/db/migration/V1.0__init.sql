--db init-- INCREASE VIDEO COUNT FUNCTION & TRIGGER-- increase video count function & trigger-- create or replace function increase_video_views()--     returns trigger as-- $$-- begin--     if tg_op = 'insert' and--        not exists (select *--                    from video_watched--                    where profile_id = new.profile_id--                      and video_id = new.video_id)--     then--         update video--         set view_count = view_count + 1--         where id = new.video_id;----         --         insert into video_watched (profile_id, video_id)-- --         values (new.profile_id, new.video_id);--     end if;----     return new;-- end;-- $$ language plpgsql;create or replace function increase_video_views()    returns trigger as$$begin    if not exists(select *                  from video_watched vw                  where vw.video_id = new.video_id                    and vw.profile_id = new.profile_id)    then    update video v    set view_count = view_count + 1    where v.id = NEW.video_id;    end if;    return NEW;end;$$ language plpgsql;create or replace trigger increase_video_views_trigger    after insert    on video_watched    for each rowexecute function increase_video_views();