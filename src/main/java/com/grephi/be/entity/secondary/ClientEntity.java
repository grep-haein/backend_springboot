package com.grephi.be.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="client", catalog = "`godhavi`")
@Entity
public class ClientEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MDB_Sno")
    private Integer mdbSno;
    private Integer SNO;
    @Column(name = "Customer_No")
    private Long CustomerNO;
    private String Family_NO;
    private Integer Family_Flag;
    private String NAME;
    private String ZIP;
    private String Address1;
    private String Address2;
    private String DDD;
    private String TEL;
    private String Memo;
    private Date Reg_Date;
    private Double Total_Money;
    private Integer Total_Rank;
    private Double Save_Point;
    private Double Save_Money;
    private Double Deposit_Money;
    private Integer Deposit_Rank;
    private String Deposit_Memo;
    private Integer Deposit_Degrade;
    private LocalDateTime Last_Visit_Date;
    @Column(name = "UnCollected_Money")
    private Double UnCollected_Money;
    private LocalDateTime Insert_Date;
    private String Insert_ID;
    private LocalDateTime Update_Date;
    private String Update_ID;
    private LocalDateTime Delete_Date;
    private String Delete_ID;
    private Integer DELETE_YN;
    private Integer Alert;
    private String Tel_list;
    private String SIGN_IMG;
    private String COMPANY;
    private Integer COM_SNO;
    private Integer Sign_Type;
    @Column(name = "birthday")
    private Date birth_Day;
    private Integer SEND_CHK;
    private Integer APP_CHK;
    private String SIGN_IMG1;
    private Integer SIGN_Type1;
    @Column(name = "visitpath")
    private String VisitPath;
    private Integer Sex;
    private Date Birthday_Date;
    private Integer cocode;
    private String asno;
    private Long asno_f;
}
