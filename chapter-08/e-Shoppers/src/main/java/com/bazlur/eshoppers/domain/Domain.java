package com.bazlur.eshoppers.domain;

import java.time.LocalDateTime;

public abstract class Domain
{
    private Long version;
    private Long id;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private LocalDateTime dateTimeUpdated = LocalDateTime.now();

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LocalDateTime getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateTimeUpdated()
    {
        return dateTimeUpdated;
    }

    public void setDateTimeUpdated(LocalDateTime dateTimeUpdated)
    {
        this.dateTimeUpdated = dateTimeUpdated;
    }
}
