DROP FUNCTION IF EXISTS fx_ismarkerinsidedeliveryzoneforannouncement;
CREATE FUNCTION fx_ismarkerinsidedeliveryzoneforannouncement(announcementPk integer, lat double precision, lng double precision)
    RETURNS boolean
    language plpgsql
as
$$
DECLARE
    polygon             record;
    previouseCoordinate record;
    coordinate          record;
    result              boolean;
begin
    SELECT false INTO result;
    CREATE TEMP TABLE annoncement_polygons
    (
        polygonid integer
    );
    INSERT INTO annoncement_polygons SELECT polygonid FROM polygon WHERE announcementid = announcementPk;
    FOR polygon IN SELECT * FROM annoncement_polygons
        LOOP
            CREATE TEMP TABLE polygon_coordinates
            (
                coordinatesid integer,
                lat           numeric,
                lng           numeric
            );

            INSERT INTO polygon_coordinates
            SELECT c.coordinatesid, c.lat, c.lng
            FROM coordinates AS c
            WHERE c.polygonid = polygon.polygonid;

            SELECT * INTO previouseCoordinate FROM polygon_coordinates ORDER BY coordinatesid DESC LIMIT 1;

            FOR coordinate IN SELECT * FROM polygon_coordinates
                LOOP
                    IF ((coordinate.lng < lng AND previouseCoordinate.lng >= lng OR
                         previouseCoordinate.lng < lng AND coordinate.lng >= lng) AND
                        (coordinate.lat + (lng - coordinate.lng) / (previouseCoordinate.lng - coordinate.lng) *
                                          (previouseCoordinate.lat - coordinate.lat) < lat))
                    THEN
                        SELECT true INTO result;
                        EXIT;
                    end if;
                end loop;
            DROP TABLE polygon_coordinates;
            IF result = true
                THEN
                EXIT;
            end if;
        end loop;
    DROP TABLE annoncement_polygons;
    RETURN result;
end;
$$

-- SELECT fx_ismarkerinsidedeliveryzoneforannouncement(18, 38.6372696408788, -95.86604116318779)