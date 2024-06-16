package com.ltnc.be.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString // tạo hàm toString cho các thuộc tính
@RequiredArgsConstructor
@MappedSuperclass // lớp con kế thừa được ánh xạ các thuộc tính vào entity nhưng không tạo bảng
// riêng
public abstract class BaseEntity { // lớp abstract
  @Id // khóa chính
  @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng
  @Column(name = "id") // tên cột trong các bảng con là id
  private Long id;

  @Column(
      name = "created_at",
      nullable = false) // nullable là không cho phép cột được tạo trong table là giá trị null
  private Long createdAt;

  @Column(name = "updated_at", nullable = false)
  private Long updatedAt;

  @PrePersist // đánh dấu là hàm sẽ được gọi trước khi các giá trị của bảng ghi được tạo vào csdl
  protected void prePersist() {
    if (this.createdAt == null)
      createdAt = Instant.now().toEpochMilli(); // đổi thời gian hiện tại thành milliseconds
    if (this.updatedAt == null) updatedAt = Instant.now().toEpochMilli();
  }

  @PreUpdate // đánh dấu là hàm sẽ được gọi trước khi cập nhật vào csdl
  protected void preUpdate() {
    this.updatedAt = Instant.now().toEpochMilli();
  }

  @PreRemove // đánh dấu là hàm sẽ được gọi trước khi bảng ghi bị xóa
  protected void preRemove() {
    this.updatedAt = Instant.now().toEpochMilli();
  }
}
