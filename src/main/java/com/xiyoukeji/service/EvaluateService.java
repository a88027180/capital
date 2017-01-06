package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.Utils;
import com.xiyoukeji.utils.Core;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/23.
 */
@Service
public class EvaluateService {
    @Resource
    BaseDao<Evaluate> evaluateBaseDao;
    @Resource
    BaseDao<EvaluateRecord> evaluateRecordBaseDao;
    @Resource
    BaseDao<EvaluateAvg> evaluateAvgBaseDao;
    @Resource
    BaseDao<FileEvaluate> fileEvaluateBaseDao;
    @Resource
    BaseDao<Project> projectBaseDao;
    @Resource
    private SessionFactory sessionFactory;

    @Transactional
    public Evaluate getEvaluate(Integer projectId, Integer userId) {
        Map map = new HashMap<>();
        map.put("projectId", projectId);
        map.put("userId", userId);
        return evaluateBaseDao.get(" from Evaluate where project.id = :projectId and user.id = :userId order by updateTime desc", map);
    }

    @Transactional
    public List<Evaluate> getEvaluateList(Integer projectId, Integer userId, int number) {
        if (userId == null) {
            SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * from (SELECT * FROM (SELECT * from evaluate LEFT OUTER JOIN project_evaluate on evaluate.id = project_evaluate.evaluate_id WHERE project_evaluate.project_id = " + projectId + ")AS a ORDER by a.`updateTime` DESC)AS b GROUP BY b.`user_id`");
            List<Evaluate> list = sqlQuery.addEntity(Evaluate.class).list();
            return list;
        } else {
            Map map = new HashMap<>();
            map.put("projectId", projectId);
            map.put("userId", userId);
            if (number == 0) {
                return evaluateBaseDao.find(" from Evaluate where project.id = :projectId and user.id = :userId order by updateTime desc", map);
            } else
                return evaluateBaseDao.find(" from Evaluate where project.id = :projectId and user.id = :userId order by updateTime desc", 1, number, map);
        }
    }

    @Transactional
    public EvaluateAvg getEvaluateAvg(Integer projectId) {
        Map map = new HashMap<>();
        map.put("projectId", projectId);
        return evaluateAvgBaseDao.get(" from EvaluateAvg where project.id = :projectId", map);
    }

    @Transactional
    public List<EvaluateAvg> getEvaluateAvgList(Integer projectId, int number) {
        Map map = new HashMap<>();
        map.put("projectId", projectId);
        if (number == 0) {
            return evaluateAvgBaseDao.find(" from EvaluateAvg where project.id = :projectId order by updateTime desc", map);
        } else
            return evaluateAvgBaseDao.find(" from EvaluateAvg where project.id = :projectId order by updateTime desc", 1, number, map);
    }

    @Transactional
    public List<EvaluateRecord> getEvaluateRecordList(int number) {
        return evaluateRecordBaseDao.find(" from EvaluateRecord order by updateTime desc", 1, number, null);
    }

    @Transactional
    public Integer saveorupdateEvaluate(Evaluate evaluate) {
        Project project = projectBaseDao.get(Project.class, evaluate.getProject().getId());
        /*想插入记录 根据project.id,user.id,quarter判断saveorupdate
        如果是save:根据project.id,quarter判断avg中saveorupdate
            save:evaluate-evaluateAvg,save
            update:setAvg,update
        如果是update:直接update evaluateAvg*/
        EvaluateRecord evaluateRecord = new EvaluateRecord();
//        EvaluateAvg evaluateAvg = new EvaluateAvg();
        evaluate.setUpdateTime(Utils.getTime());
        evaluate.setQuarter(Utils.getQuarter());

        /*record*/
        try {
            Utils.reflectionAttr(evaluate, evaluateRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        evaluateRecordBaseDao.save(evaluateRecord);

        Map map = new HashMap<>();
        map.put("projectId", evaluate.getProject().getId());
        map.put("userId", evaluate.getUser().getId());
        map.put("quarter", evaluate.getQuarter());
        Evaluate evaluate1 = evaluateBaseDao.get("from Evaluate where project.id = :projectId and user.id = :userId and quarter = :quarter", map);
        EvaluateAvg evaluateAvg1 = evaluateAvgBaseDao.get("from EvaluateAvg where project.id = " + evaluate.getProject().getId() + " and quarter = '" + evaluate.getQuarter() + "'");
        if (evaluate1 == null) {
            evaluate1 = evaluate;
            /*save*/
            if (evaluateAvg1 == null) {
                /*新建evaluate 新建evaluateAvg*/
                try {
                    evaluateAvg1 = new EvaluateAvg();
                    Utils.reflectionAttr(evaluate, evaluateAvg1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                /*新建evaluate 更新evaluateAvg*/
                evaluateAvg1.setItem_all(evaluateAvg1.getItem_all() + evaluate1.getItem_all());
                evaluateAvg1.setItem_one(evaluateAvg1.getItem_one() + evaluate1.getItem_one());
                evaluateAvg1.setItem_two(evaluateAvg1.getItem_two() + evaluate1.getItem_two());
                evaluateAvg1.setItem_three(evaluateAvg1.getItem_three() + evaluate1.getItem_three());
                evaluateAvg1.setItem_four(evaluateAvg1.getItem_four() + evaluate1.getItem_four());
                evaluateAvg1.setItem_five(evaluateAvg1.getItem_five() + evaluate1.getItem_five());
                evaluateAvg1.setItem_six(evaluateAvg1.getItem_six() + evaluate1.getItem_six());
                evaluateAvg1.setItem_seven(evaluateAvg1.getItem_seven() + evaluate1.getItem_seven());
                evaluateAvg1.setItem_eight(evaluateAvg1.getItem_eight() + evaluate1.getItem_eight());
                evaluateAvg1.setItem_nine(evaluateAvg1.getItem_nine() + evaluate1.getItem_nine());
                evaluateAvg1.setItem_ten(evaluateAvg1.getItem_ten() + evaluate1.getItem_ten());
                evaluateAvg1.setNumber(evaluateAvg1.getNumber() + 1);
                evaluateAvg1.setUpdateTime(Utils.getTime());
            }


        } else {
/*更新evaluate 新建evaluateAvg*/
            evaluateAvg1.setItem_all(evaluateAvg1.getItem_all() - evaluate1.getItem_all() + evaluate.getItem_all());
            evaluateAvg1.setItem_one(evaluateAvg1.getItem_one() - evaluate1.getItem_one() + evaluate.getItem_one());
            evaluateAvg1.setItem_two(evaluateAvg1.getItem_two() - evaluate1.getItem_two() + evaluate.getItem_two());
            evaluateAvg1.setItem_three(evaluateAvg1.getItem_three() - evaluate1.getItem_three() + evaluate.getItem_three());
            evaluateAvg1.setItem_four(evaluateAvg1.getItem_four() - evaluate1.getItem_four() + evaluate.getItem_four());
            evaluateAvg1.setItem_five(evaluateAvg1.getItem_five() - evaluate1.getItem_five() + evaluate.getItem_five());
            evaluateAvg1.setItem_six(evaluateAvg1.getItem_six() - evaluate1.getItem_six() + evaluate.getItem_six());
            evaluateAvg1.setItem_seven(evaluateAvg1.getItem_seven() - evaluate1.getItem_seven() + evaluate.getItem_seven());
            evaluateAvg1.setItem_eight(evaluateAvg1.getItem_eight() - evaluate1.getItem_eight() + evaluate.getItem_eight());
            evaluateAvg1.setItem_nine(evaluateAvg1.getItem_nine() - evaluate1.getItem_nine() + evaluate.getItem_nine());
            evaluateAvg1.setItem_ten(evaluateAvg1.getItem_ten() - evaluate1.getItem_ten() + evaluate.getItem_ten());
            evaluateAvg1.setUpdateTime(Utils.getTime());

            evaluate1.setItem_all(evaluate.getItem_all());
            evaluate1.setItem_one(evaluate.getItem_one());
            evaluate1.setItem_two(evaluate.getItem_two());
            evaluate1.setItem_three(evaluate.getItem_three());
            evaluate1.setItem_four(evaluate.getItem_four());
            evaluate1.setItem_five(evaluate.getItem_five());
            evaluate1.setItem_six(evaluate.getItem_six());
            evaluate1.setItem_seven(evaluate.getItem_seven());
            evaluate1.setItem_eight(evaluate.getItem_eight());
            evaluate1.setItem_nine(evaluate.getItem_nine());
            evaluate1.setItem_ten(evaluate.getItem_ten());
            evaluate1.setUpdateTime(Utils.getTime());
            evaluate1.setQuarter(Utils.getQuarter());


        }
        int item_all = evaluateAvg1.getItem_all();
        int number = evaluateAvg1.getNumber();
        int avg = item_all / number / 10;
        FileEvaluate fileEvaluate = null;
        switch (avg) {
            case 110:
            case 100:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 1);
                break;
            case 99:
            case 98:
            case 97:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 2);
                break;
            case 96:
            case 95:
            case 94:
            case 93:
            case 92:
            case 91:
            case 90:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 3);
                break;
            case 89:
            case 88:
            case 87:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 4);
                break;
            case 86:
            case 85:
            case 84:
            case 83:
            case 82:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 5);
                break;
            case 81:
            case 80:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 6);
                break;
            case 79:
            case 78:
            case 77:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 7);
                break;
            case 76:
            case 75:
            case 74:
            case 73:
            case 72:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 8);
                break;
            case 71:
            case 70:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 9);
                break;
            default:
                fileEvaluate = fileEvaluateBaseDao.get(FileEvaluate.class, 10);
                break;

        }
        if (fileEvaluate != null) {
            evaluateAvg1.setFileEvaluate(fileEvaluate);
            project.setEvaluateAvg(evaluateAvg1);
        }

        evaluateAvgBaseDao.saveOrUpdate(evaluateAvg1);
        evaluateBaseDao.saveOrUpdate(evaluate1);
        return evaluate.getProject().getId();
    }

}
