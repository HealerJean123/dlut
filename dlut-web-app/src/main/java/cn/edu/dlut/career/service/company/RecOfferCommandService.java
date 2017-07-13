package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
public interface RecOfferCommandService {



    /**
     * 保存、更新offer信息
     * @param recOffer
     */
    void save(RecOffer recOffer);

    //审核学生offer
    void update(UUID uuid, String auditStatus, String stuReceivingStatus);


    /**
     * @author wei  2017/6/6
     * @method sendOffer
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description  企业发送offer
     */
    RecOffer sendOffer(UUID stuId, UUID templateId);

    /**
     * @Autor 史念念 2017/6/5
     * @Description 企业端 取消已发送的offer
     * @param id offer id
     * @return
     */
    String deleteOffer(UUID id);

    /** @Autor 史念念 2017/6/6
     * @Description 企业端 再次发送offer
     * @param stuId 学生id
     * @param templateId offer模板id
     * @return
     */
    RecOffer sendOfferAgain(UUID stuId, UUID templateId);
}
