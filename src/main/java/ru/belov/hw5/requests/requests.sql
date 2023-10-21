--  #1 - Выдайте всю информацию о спортсменах из таблицы sportsman.
SELECT *
FROM trainee.t_sportsman;
--  #2 - Выдайте наименование и мировые результаты по всем соревнованиям.
SELECT c.competition_name, d.discipline_description, d.world_record
FROM trainee.t_competition c
         INNER JOIN trainee.t_competitions_disciplines cd ON c.competition_id = cd.competition_id
         INNER JOIN trainee.t_discipline d USING (discipline_id);
-- #3 - Выберите имена всех спортсменов, которые родились в 1990 году.
SELECT s.sportsman_name
FROM trainee.t_sportsman s
WHERE s.year_of_birth = 1990;
-- #4 - Выберите наименование и мировые результаты
-- по всем соревнованиям, установленные 12-05-2010 или 15-05-2010.
SELECT c.competition_name, d.discipline_description, d.world_record, c.hold_date
FROM trainee.t_competition c
         INNER JOIN trainee.t_competitions_disciplines cd ON c.competition_id = cd.competition_id
         INNER JOIN trainee.t_discipline d USING (discipline_id)
WHERE d.set_date = '12-05-2010'
   OR d.set_date = '15-05-2010';
-- #5 - Выберите дату проведения всех соревнований, которые проводились в Москве и полученные на них результаты равны 10 секунд.
SELECT c.competition_name, c.hold_date
FROM trainee.t_competition c
         INNER JOIN trainee.t_result r ON r.competition_id = c.competition_id
WHERE c.city = 'Москва'
  AND r.result = 10;
-- #6 - Выберите имена всех спортсменов, у которых персональный рекорд не равен 25 с.
SELECT s.sportsman_name, sr.record_value
FROM trainee.t_sportsman s
         INNER JOIN trainee.t_sportsman_record sr ON sr.sportsman_id = s.sportsman_id
WHERE sr.record_value <> 25;
-- #7 - Выберите названия всех соревнований(дисциплин), у которых мировой рекорд равен 15 с
-- и дата установки рекорда не равна 12-02-2015.
SELECT d.discipline_description
FROM trainee.t_discipline d
WHERE d.world_record = 15
  AND d.set_date <> '12-02-2015';
-- #8 - Выберите города проведения соревнований, где результаты (не)принадлежат множеству {13, 25, 17, 9}.
SELECT c.city
FROM trainee.t_competition c
         INNER JOIN trainee.t_result r USING (competition_id)
WHERE r.result NOT IN (13, 25, 17, 9);

-- #9 - Выберите имена всех спортсменов, у которых год рождения 2000 и разряд не принадлежит множеству {3, 7, 9}
SELECT s.sportsman_name
FROM trainee.t_sportsman s
WHERE s.year_of_birth = 2000
  AND s.rank NOT IN (3, 7, 9);
-- #10 - Выберите дату проведения всех соревнований, у которых город проведения начинается с буквы "М".
SELECT hold_date
FROM trainee.t_competition
WHERE city LIKE 'М%';
-- #11 - Выберите имена всех спортсменов, у которых имена начинаются с буквы "М" (P)
-- и год рождения не заканчивается на "6".
SELECT *
FROM trainee.t_sportsman
WHERE sportsman_name LIKE 'P%'
  AND CAST(year_of_birth AS VARCHAR) NOT LIKE '%6';
-- #12 - Выберите наименования всех соревнований, у которых в названии есть слово "международные"(мира).
SELECT c.competition_name
FROM trainee.t_competition c
WHERE competition_name LIKE '%мира%';
-- #13 - Выберите годы рождения всех спортсменов без повторений.
SELECT DISTINCT s.year_of_birth
FROM trainee.t_sportsman s;
-- #14 - Найдите количество результатов, полученных 12-05-2014.
SELECT d.set_date, COUNT(d.set_date) as quantity
FROM trainee.t_discipline d
WHERE d.set_date = '2014-05-12'
GROUP BY d.set_date;
-- #15 - Вычислите максимальный результат, полученный в Москве.
SELECT MAX(r.result)
FROM trainee.t_competition c
         INNER JOIN trainee.t_result r USING (competition_id)
WHERE c.city = 'Москва';
-- А если хочу всю строку вывести целиком
SELECT *
FROM trainee.t_competition c
         INNER JOIN trainee.t_result r USING (competition_id)
WHERE r.result = (SELECT MAX(result) FROM trainee.t_result WHERE city = 'Москва')
  AND c.city = 'Москва';
-- #16 - Вычислите минимальный год рождения спортсменов, которые имеют 1 разряд.
SELECT MIN(year_of_birth)
FROM trainee.t_sportsman
WHERE rank = 1;
-- #17 - Определите имена спортсменов, у которых личные рекорды совпадают с результатами,
-- установленными 12-05-2014.
SELECT s.sportsman_name
FROM trainee.t_sportsman s
         INNER JOIN trainee.t_sportsman_record sr USING (sportsman_id)
WHERE sr.record_value IN (SELECT world_record FROM trainee.t_discipline WHERE set_date = '2014-05-12');
-- #18 - Выведите наименования соревнований, у которых дата установления мирового рекорда совпадает
-- с датой проведения соревнований в Москве 12-05-2014.(не особо понятен запрос, выведу все соревнования на которых был поставлен рекорд,
-- то есть дата установления рекорда совпадает с датой проведения соревнования)
SELECT DISTINCT c.competition_name
FROM trainee.t_competition c
         INNER JOIN trainee.t_competitions_disciplines cd USING (competition_id)
         INNER JOIN trainee.t_discipline d ON cd.discipline_id = d.discipline_id
WHERE c.hold_date = d.set_date;
-- #19 - Вычислите средний результат каждого из спортсменов.
SELECT s.sportsman_id, s.sportsman_name, AVG(sr.record_value) AS avg_result
FROM trainee.t_sportsman s
         INNER JOIN trainee.t_sportsman_record sr ON s.sportsman_id = sr.sportsman_id
GROUP BY s.sportsman_id, s.sportsman_name;
-- #20 - Выведите годы рождения спортсменов, у которых результат, показанный в Москве
-- выше среднего по всем спортсменам;
SELECT s.year_of_birth
FROM trainee.t_competition
         INNER JOIN trainee.t_result r USING (competition_id)
         INNER JOIN trainee.t_sportsman s USING (sportsman_id)
WHERE city = 'Москва'
  AND r.result > (SELECT AVG(r.result)
                  FROM trainee.t_competition
                           INNER JOIN trainee.t_result r USING (competition_id)
                  WHERE city = 'Москва');